package com.lcy.projectscheduler.api.v1.domain.invitation;

import com.lcy.projectscheduler.api.v1.domain.project.ProjectService;
import com.lcy.projectscheduler.api.v1.domain.project.Project;
import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.dto.CreateProjectDTO;
import com.lcy.projectscheduler.api.v1.repository.UserRepository;
import com.lcy.projectscheduler.exception.HasNotPermissionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class InvitationServiceTest {

    private final CreateProjectDTO createProjectDTO = CreateProjectDTO.builder()
            .title("testTitle")
            .subject("testSubject")
            .deadlineDate(LocalDate.now().minusDays(1))
            .startDate(LocalDate.now().plusDays(1))
            .build();

    private User sender = User.builder()
            .email("test@test.com")
            .pw("test")
            .build();

    private User receiver = User.builder()
            .email("test2@test.com")
            .pw("test2")
            .build();


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ProjectService createProjectService;

    @Test
    @DisplayName("성공")
    void invite() {
        // given
        sender = userRepository.save(sender);

        receiver = userRepository.save(receiver);

        createProjectDTO.setManager(sender.getId());

        Project project = createProjectService.create(createProjectDTO);

        // when
        Invitation invitation = invitationService.invite(sender.getId(), project.getId(), receiver.getId());

        // then
        assertThat(invitation.getId()).isNotNull();
    }

    @Test
    @DisplayName("권한 실패")
    void fail_invite() {
        // given
        sender = userRepository.save(sender);
        receiver = userRepository.save(receiver);

        createProjectDTO.setManager(sender.getId());

        Project project = createProjectService.create(createProjectDTO);

        // when
        assertThrows(HasNotPermissionException.class, () -> invitationService.invite(receiver.getId(), project.getId(), sender.getId()));
    }
}