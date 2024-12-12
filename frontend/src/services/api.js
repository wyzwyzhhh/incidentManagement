import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getIncidents = (page = 0, size = 10) => {
  return api.get(`/incidents?page=${page}&size=${size}`);
};

export const getIncident = (id) => {
  return api.get(`/incidents/${id}`);
};

export const createIncident = (incident) => {
  return api.post('/incidents', incident);
};

export const updateIncident = (id, incident) => {
  return api.put(`/incidents/${id}`, incident);
};

export const deleteIncident = (id) => {
  return api.delete(`/incidents/${id}`);
};

export default api; 