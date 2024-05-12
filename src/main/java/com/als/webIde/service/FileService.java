package com.als.webIde.service;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    private final DockerService dockerService;

    public FileService(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    public void saveFile(String userId, String filename, String content) {
        String containerId = dockerService.findContainerByUserId(userId);
        if (containerId == null) {
            throw new IllegalStateException("No container found for user");
        }
        String command = String.format("echo \"%s\" > /workspace/%s", escapeShellArg(content), filename);
        dockerService.executeCommand(containerId, command);
    }

    public String readFile(String userId, String filename) {
        String containerId = dockerService.findContainerByUserId(userId);
        String command = String.format("cat /workspace/%s", filename);
        return dockerService.executeCommand(containerId, command);
    }

    public void deleteFile(String userId, String filename) {
        String containerId = dockerService.findContainerByUserId(userId);
        String command = String.format("rm /workspace/%s", filename);
        dockerService.executeCommand(containerId, command);
    }

    private String escapeShellArg(String arg) {
        return arg.replace("\"", "\\\"");
    }
}