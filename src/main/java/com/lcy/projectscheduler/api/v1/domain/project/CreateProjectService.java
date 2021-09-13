package com.lcy.projectscheduler.api.v1.domain.project;

import com.lcy.projectscheduler.api.v1.domain.user.User;
import com.lcy.projectscheduler.api.v1.dto.CreateProjectDTO;
import org.springframework.stereotype.Service;

@Service
public class CreateProjectService {

    public Project create(CreateProjectDTO createProjectDTO) {
        User user = null;

        Project newProject = Project.create(createProjectDTO, user);

        return newProject;
    }
}
