import { httpClient } from "@/api";
import { ApiResponse, Task, TaskCreateDTO, TaskEditDTO } from "@/models";

export const getAllTaskAPI = async (): Promise<Task[]> => {
  const response = await httpClient.get<ApiResponse<Task[]>>("/tasks");
  const { data, status, message } = response.data;

  if (!status) throw new Error(message);
  return data ?? [];
};

export const getAllTaskByStatusAPI = async (statusParams: string): Promise<Task[]> => {
  const response = await httpClient.get<ApiResponse<Task[]>>(`/tasks/filter?status=${statusParams}`);
  const { data, status, message } = response.data;

  if (!status) throw new Error(message);
  return data ?? [];
};


export const createTaskAPI = async (payload: TaskCreateDTO): Promise<Task> => {
  const response = await httpClient.post<ApiResponse<Task>>("/tasks", payload);
  const { data, status, message } = response.data;

  if (!status) throw new Error(message);
  return data!;
};

export const updateTaskAPI = async (id: number, payload: Partial<TaskEditDTO>): Promise<ApiResponse<Task>> => {
  const response = await httpClient.put<ApiResponse<Task>>(`/tasks/${id}`, payload);  
  const data = response.data;
  return data;
};


export const deleteTaskAPI = async (id: number): Promise<ApiResponse<null>> => {
  const response = await httpClient.delete<ApiResponse<null>>(`/tasks/${id}`);
  const { status, message, data } = response.data;

  if (!status) throw new Error(message);
  return { status, message, data };
};
