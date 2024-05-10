package com.als.webIde.service;

public interface DockerService {
    /**
     * 유저에게 도커 컨테이너 생성
     * @param userId
     * @return 컨테이너 ID
     */
    String createAndStartContainer(String userId);

    /**
     * 유저 컨테이너 종료 및 삭제
     * @param containerId 종료되는 컨테이너 ID
     */
    void stopAndRemoveContainer(String containerId);

    void test();
}