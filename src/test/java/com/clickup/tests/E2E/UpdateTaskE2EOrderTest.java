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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UpdateTaskE2EOrderTest {

    private static String spaceName = "Space name E2E";
    private static String listName = "List name E2E";
    private static String taskName = "Task name E2E";
    private static String taskUpdateName = "New task name E2E";
    private static String taskUpdateDescription = "New task description E2E";
    private static String spaceId;
    private static String listId;
    private static String taskId;
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2EOrderTest.class);


    @Test
    @Order(1)
    void createSpaceE2E() {
        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final Response createSpaceResponse = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(createSpaceResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath spaceJson = createSpaceResponse.jsonPath();
        Assertions.assertThat(spaceJson.getString("name")).isEqualTo(spaceName);
        spaceId = spaceJson.getString("id");
    }

    @Test
    @Order(2)
    void createListStep() {
        JSONObject list = new JSONObject();
        list.put("name", listName);
        final Response createListResponse = CreateListRequest.createList(list, spaceId);
        Assertions.assertThat(createListResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath listJson = createListResponse.jsonPath();
        Assertions.assertThat(listJson.getString("name")).isEqualTo(listName);
        listId = listJson.getString("id");
    }

    @Test
    @Order(3)
    void createTaskStep() {
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
        taskId = taskJson.getString("id");

    }

    @Test
    @Order(4)
    void updateTaskStep() {
        JSONObject task = new JSONObject();
        task.put("name", taskUpdateName);
        task.put("description", taskUpdateDescription);

        final Response updateTaskResponse = UpdateTaskRequest.updateTask(task, taskId);
        Assertions.assertThat(updateTaskResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath taskUpdateJson = updateTaskResponse.jsonPath();
        Assertions.assertThat(taskUpdateJson.getString("name")).isEqualTo(taskUpdateName);
        Assertions.assertThat(taskUpdateJson.getString("description")).isEqualTo(taskUpdateDescription);
    }

    @Test
    @Order(5)
    void updateTaskStatusStep() {
        JSONObject task = new JSONObject();
        task.put("status", "complete");

        final Response updateTaskResponse = UpdateTaskRequest.updateTask(task, taskId);
        Assertions.assertThat(updateTaskResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        JsonPath taskUpdateStatusJson = updateTaskResponse.jsonPath();
        Assertions.assertThat(taskUpdateStatusJson.getString("status.status")).isEqualTo("complete");
    }

    @Test
    @Order(6)
    void deleteSpace() {
        Response deleteSpaceResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteSpaceResponse.statusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
