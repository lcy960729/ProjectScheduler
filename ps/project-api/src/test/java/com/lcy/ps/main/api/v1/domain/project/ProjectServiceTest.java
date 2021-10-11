//package com.lcy.ps.main.api.v1.domain.project;
//
//import com.lcy.ps.core.domain.member.permission.ProjectPermission;
//import com.lcy.ps.core.domain.member.state.MemberState;
//import com.lcy.ps.core.domain.user.User;
//import com.lcy.ps.core.dto.request.project.CreateProjectDTO;
//import com.lcy.ps.core.repository.UserRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@DisplayName("프로젝트 도메인 서비스 테스트")
//class ProjectServiceTest {
//
//    @Autowired
//    private ProjectService projectService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private CreateProjectDTO createProjectDTO = CreateProjectDTO.builder()
//            .title("testTitle")
//            .subject("testSubject")
//            .deadlineDate(LocalDate.now().minusDays(1))
//            .startDate(LocalDate.now().plusDays(1))
//            .build();
//
//    private User user = User.builder()
//            .email("test@test.com")
//            .pw("test")
//            .build();
//
//    @BeforeEach
//    void setUp() {
//        user = userRepository.save(user);
//    }
//
//    @Nested
//    @DisplayName("성공 테스트")
//    public class Success {
//        @Test
//        @DisplayName("프로젝트를 생성할때")
//        void create() {
//            // when
//            Project project = projectService.create(user.getId(), createProjectDTO);
//
//            // then
//            Assertions.assertThat(project).isNotNull();
//
//            Set<ProjectMember> participants = project.getProjectMembers();
//
//            Assertions.assertThat(participants).hasSize(1);
//
//            ProjectMember projectMember = participants.stream().findFirst().get();
//
//            Assertions.assertThat(projectMember.getState()).isEqualTo(MemberState.JOINED);
//            Assertions.assertThat(projectMember.getProjectPermission()).isEqualTo(ProjectPermission.SUPER_MANAGER);
//        }
//    }
//}