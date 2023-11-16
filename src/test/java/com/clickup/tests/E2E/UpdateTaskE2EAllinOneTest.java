package com.clickup.tests.E2E;

import com.clickup.requests.list.CreateListRequest;
import com.clickup.requests.space.CreateSpaceRequest;
import com.clickup.requests.space.DeleteSpaceRequest;
import com.clickup.requests.task.CreateTaskRequest;
import com.clickup.requests.task.UpdateTaskRequest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class UpdateTaskE2EAllinOneTest {

    private static String spaceName = "Space name E2E";
    private static String listName = "List name E2E";
    private static String taskName = "Task name E2E";
    private static String taskUpdateName = "New task name E2E";
    private static String taskUpdateDescription = "New task description E2E";


    private static String spaceId;
    private static String listId;
    private static String taskId;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2EAllinOneTest.class);


    @Test
    void updateTaskE2E() {

        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);
        listId = createListStep();
        LOGGER.info("List created with id: {}", listId);
        taskId = createTaskStep();
        LOGGER.info("Task created with id: {}", taskId);
        updateTaskStep();
        LOGGER.info("Task updated with id: '{}', name: '{}', description: '{}'", taskId, taskUpdateName, taskUpdateDescription);
        updateTaskStatusStep();
        deleteSpace();

    }

    private String createSpaceStep() {
        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final Response createSpaceResponse = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(createSpaceResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath spaceJson = createSpaceResponse.jsonPath();
        Assertions.assertThat(spaceJson.getString("name")).isEqualTo(spaceName);
//        Assertions.assertThat(createSpaceResponse.jsonPath().getString("name")).isEqualTo(spaceName);

        return spaceJson.getString("id");
    }

    private String createListStep() {
        JSONObject list = new JSONObject();
        list.put("name", listName);
        final Response createListResponse = CreateListRequest.createList(list, spaceId);
        Assertions.assertThat(createListResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath listJson = createListResponse.jsonPath();
        Assertions.assertThat(listJson.getString("name")).isEqualTo(listName);

        return listJson.getString("id");
    }

    private String createTaskStep() {
        JSONObject task = new JSONObject();
        task.put("name", taskName);
        task.put("description", "taskDescription");
        task.put("status", "to do");
        task.put("priority", JSONObject.NULL);
        task.put("parent", JSONObject.NULL);
        task.put("time_estimate", JSONObject.NULL);
        task.put("assignees", JSONObject.NULL);
        task.put("archived", false);


        final Response createTaskResponse = CreateTaskRequest.createTask(task, listId);
        Assertions.assertThat(createTaskResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath taskJson = createTaskResponse.jsonPath();
        Assertions.assertThat(taskJson.getString("name")).isEqualTo(taskName);


        return taskJson.getString("id");
    }

    private void updateTaskStep() {
        JSONObject task = new JSONObject();
        task.put("name", taskUpdateName);
        task.put("description", taskUpdateDescription);

        final Response updateTaskResponse = UpdateTaskRequest.updateTask(task, taskId);
        Assertions.assertThat(updateTaskResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath taskUpdateJson = updateTaskResponse.jsonPath();
        Assertions.assertThat(taskUpdateJson.getString("name")).isEqualTo(taskUpdateName);
        Assertions.assertThat(taskUpdateJson.getString("description")).isEqualTo(taskUpdateDescription);
    }

    private void updateTaskStatusStep() {
        JSONObject task = new JSONObject();
        task.put("status", "complete");

        final Response updateTaskResponse = UpdateTaskRequest.updateTask(task, taskId);
        Assertions.assertThat(updateTaskResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath taskUpdateStatusJson = updateTaskResponse.jsonPath();
        Assertions.assertThat(taskUpdateStatusJson.getString("status.status")).isEqualTo("complete");
    }

    private void deleteSpace() {
        Response deleteSpaceResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteSpaceResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
