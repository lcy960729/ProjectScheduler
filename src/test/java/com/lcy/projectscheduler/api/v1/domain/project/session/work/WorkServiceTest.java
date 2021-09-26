package com.lcy.projectscheduler.api.v1.domain.project.session.work;

import com.lcy.projectscheduler.api.v1.domain.invitation.Invitation;
import com.lcy.projectscheduler.api.v1.domain.invitation.InvitationService;
import com.lcy.projectscheduler.api.v1.domain.member.permission.SessionPermission;
import com.lcy.projectscheduler.api.v1.domain.member.permission.WorkPermission;
import com.lcy.projectscheduler.api.v1.domain.member.state.MemberState;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.project.ProjectService;
import com.lcy.projectscheduler.api.v1.domain.project.session.Session;
import com.lcy.projectscheduler.api.v1.domain.project.session.SessionMember;
import com.lcy.projectscheduler.api.v1.domain.project.session.SessionService;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.dto.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.dto.CreateSessionDTO;
import com.lcy.projectscheduler.api.v1.dto.CreateWorkDTO;
import com.lcy.projectscheduler.api.v1.repository.UserRepository;
import com.lcy.projectscheduler.exception.HasNotPermissionException;
import com.lcy.projectscheduler.exception.NotRegisteredMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("워크 서비스 테스트")
class WorkServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private WorkService workService;

    @Autowired
    private InvitationService invitationService;

    private CreateProjectDTO createProjectDTO = CreateProjectDTO.builder()
            .title("testTitle")
            .subject("testSubject")
            .deadlineDate(LocalDate.now().minusDays(1))
            .startDate(LocalDate.now().plusDays(1))
            .build();

    private CreateSessionDTO createSessionDTO = CreateSessionDTO.builder()
            .title("testTitle")
            .subject("testSubject")
            .deadlineDate(LocalDateTime.now().minusDays(1))
            .startDate(LocalDateTime.now().plusDays(1))
            .build();

    private CreateWorkDTO createWorkDTO = CreateWorkDTO.builder()
            .title("testTitle")
            .contents("testContents")
            .priority(1)
            .deadlineDate(LocalDateTime.now().minusDays(1))
            .startDate(LocalDateTime.now().plusDays(1))
            .build();

    private User user = User.builder()
            .email("test@test.com")
            .pw("test")
            .build();

    private User other = User.builder()
            .email("test2@test.com")
            .pw("test2")
            .build();

    private Project project;
    private Session session;

    @BeforeEach
    void setUp() {
        user = userRepository.save(user);
        other = userRepository.save(other);

        createProjectDTO.setManager(user.getId());
        createSessionDTO.setManager(user.getId());
        createWorkDTO.setWorker(user.getId());

        project = projectService.create(createProjectDTO);
        session = sessionService.create(project.getId(), createSessionDTO);
    }

    @Nested
    @DisplayName("성공 테스트")
    public class Success {
        @Test
        @DisplayName("권한을 가진 사용자가 세션을 생성할때")
        void create() {
            // given

            // when
            Work work = workService.create(user.getId(), project.getId(), session.getId(), createWorkDTO);

            // then
            assertThat(work).isNotNull();

            Set<Worker> workers = work.getWorkers();

            assertThat(workers).hasSize(1);

            Worker worker = workers.stream().findFirst().get();

            assertThat(worker.getState()).isEqualTo(MemberState.JOINED);
            assertThat(worker.getWorkPermission()).isEqualTo(WorkPermission.WORKER);
        }
    }

    @Nested
    @DisplayName("실패 테스트")
    public class Fail {
        @Test
        @DisplayName("프로젝트에 가입되지 않은 사용자가 워크를 생성할때")
        void create_NotJoinedProject() {
            // given
            createWorkDTO.setWorker(other.getId());

            // when
            Throwable throwable = catchThrowable(() -> workService.create(other.getId(), project.getId(), session.getId(), createWorkDTO));

            // then
            assertThat(throwable).isInstanceOf(NotRegisteredMemberException.class);
        }

        @Test
        @DisplayName("프로젝트에 가입되있지만 세션에 참여하지 않은 사용자가 워크를 생성할때")
        void create_NotJoinedSession() {
            // given
            Invitation invitation = invitationService.invite(user.getId(), other.getId(), project.getId());
            invitationService.accept(other.getId(), invitation.getId());

            // when
            Throwable throwable = catchThrowable(() -> workService.create(other.getId(), project.getId(), session.getId(), createWorkDTO));

            // then
            assertThat(throwable).isInstanceOf(NotRegisteredMemberException.class);
        }
    }
}