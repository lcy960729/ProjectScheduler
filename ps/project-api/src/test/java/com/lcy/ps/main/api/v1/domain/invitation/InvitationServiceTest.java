//package com.lcy.ps.main.api.v1.domain.invitation;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.catchThrowable;
//
//@SpringBootTest
//@DisplayName("초대장 도메인 서비스 테스트")
//class InvitationServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private InvitationService invitationService;
//
//    @Autowired
//    private ProjectService createProjectService;
//
//    private final CreateProjectDTO createProjectDTO = CreateProjectDTO.builder()
//            .title("testTitle")
//            .subject("testSubject")
//            .deadlineDate(LocalDate.now().minusDays(1))
//            .startDate(LocalDate.now().plusDays(1))
//            .build();
//
//    private User superManager = User.builder()
//            .email("test@test.com")
//            .pw("test")
//            .build();
//
//    @BeforeEach
//    void setUp() {
//        superManager = userRepository.save(superManager);
//        receiver = userRepository.save(receiver);
//        other = userRepository.save(other);
//
//    }
//
//    private User receiver = User.builder()
//            .email("test2@test.com")
//            .pw("test2")
//            .build();
//
//    private User other = User.builder()
//            .email("test2@test.com")
//            .pw("test2")
//            .build();
//
//    @Nested
//    @DisplayName("실패 테스트")
//    public class FailTest {
//        @Test
//        @DisplayName("초대 권한이 없는 사용자가 프로젝트에 새로운 사용자를 초대할때")
//        @Transactional
//        void fail_invite() {
//            // given
//            Project project = createProjectService.create(superManager.getId(),createProjectDTO);
//
//            // when
//            Throwable throwable = catchThrowable(() -> invitationService.invite(other.getId(), receiver.getId(), project.getId()));
//
//            // then
//            assertThat(throwable).isInstanceOfAny(HasNotPermissionException.class, NotRegisteredMemberException.class);
//        }
//
//
//        @Test
//        @DisplayName("이미 처리된 초대장에 대해 수락할때")
//        @Transactional
//        void reject_alreadyProcessed() {
//            // given
//            Project project = createProjectService.create(superManager.getId(),createProjectDTO);
//            Invitation invitation = invitationService.invite(superManager.getId(), receiver.getId(), project.getId());
//
//            // when
//            invitationService.accept(receiver.getId(), invitation.getId());
//            Throwable throwable = catchThrowable(() -> invitationService.accept(receiver.getId(), invitation.getId()));
//
//            // then
//            assertThat(throwable).isInstanceOf(AlreadyProcessedInvitationException.class);
//        }
//
//        @Test
//        @DisplayName("이미 처리된 초대장에 대해 거절할때")
//        @Transactional
//        void accept_alreadyProcessed() {
//            // given
//            Project project = createProjectService.create(superManager.getId(),createProjectDTO);
//            Invitation invitation = invitationService.invite(superManager.getId(), receiver.getId(), project.getId());
//
//            // when
//            invitationService.reject(receiver.getId(), invitation.getId());
//            Throwable throwable = catchThrowable(() -> invitationService.reject(receiver.getId(), invitation.getId()));
//
//            // then
//            assertThat(throwable).isInstanceOf(AlreadyProcessedInvitationException.class);
//        }
//    }
//
//    @Nested
//    @DisplayName("성공 테스트")
//    public class SuccessTest {
//        @Test
//        @DisplayName("초대 권한을 가진 사용자가 다른 사용자를 초대할때")
//        @Transactional
//        void invite() {
//            // given
//            Project project = createProjectService.create(superManager.getId(),createProjectDTO);
//
//            // when
//            Invitation invitation = invitationService.invite(superManager.getId(), receiver.getId(), project.getId());
//
//            // then
//            Assertions.assertThat(invitation.getId()).isNotNull();
//            assertThat(invitation.getState()).isEqualTo(InvitationState.WAITING);
//        }
//
//
//        @Test
//        @DisplayName("초대 받은 사용자가 초대장을 수락할때")
//        @Transactional
//        void accept() {
//            // given
//            Project project = createProjectService.create(superManager.getId(),createProjectDTO);
//            Invitation invitation = invitationService.invite(superManager.getId(), receiver.getId(), project.getId());
//
//            // when
//            invitationService.accept(receiver.getId(), invitation.getId());
//
//            // then
//            assertThat(invitation.getState()).isEqualTo(InvitationState.DONE);
//            Assertions.assertThat(project.getProjectMembers()).hasSize(2);
//        }
//
//        @Test
//        @DisplayName("초대 받은 사용자가 초대장을 거절할때")
//        @Transactional
//        void reject() {
//            // given
//            Project project = createProjectService.create(superManager.getId(),createProjectDTO);
//            Invitation invitation = invitationService.invite(superManager.getId(), receiver.getId(), project.getId());
//
//            // when
//            invitationService.reject(receiver.getId(), invitation.getId());
//
//            // then
//            assertThat(invitation.getState()).isEqualTo(InvitationState.REJECT);
//            Assertions.assertThat(project.getProjectMembers()).hasSize(1);
//        }
//    }
//}