package com.als.webIde.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.HostConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j // 롬복 Logger
@RequiredArgsConstructor // final 처리
@Service
public class DockerServiceImpl implements DockerService {

    private final DockerClient dockerClient;

    @Override
    public String createAndStartContainer(String userId) {
        try {
            CreateContainerResponse container = dockerClient.createContainerCmd("khv9786/als_ide_jdk")
                    .withHostConfig(HostConfig.newHostConfig().withAutoRemove(true))
                    .withCmd("java", "-jar", "your-application.jar")
                    .exec();

            dockerClient.startContainerCmd(container.getId()).exec();
            return container.getId();
        } catch (Exception e) {
            log.error("컨테이너 생성에 실패했습니다", e);
            return null;
        }
    }

    @Override
    public void stopAndRemoveContainer(String containerId) {
        try {
            dockerClient.stopContainerCmd(containerId).exec();
            dockerClient.removeContainerCmd(containerId).exec();
        } catch (Exception e) {
            log.error("컨테이너 종료에 실패했습니다.", e);
        }
    }

    @Override
    public void test() {
        dockerClient.pingCmd().exec();
    }
}