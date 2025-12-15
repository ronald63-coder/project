import axios from 'axios'

const API_BASE = import.meta.env.VITE_API_BASE || '' // default same origin

const api = axios.create({
  baseURL: API_BASE + '/api',
  withCredentials: true
})

// attach token if present
api.interceptors.request.use(config => {
  const token = localStorage.getItem('token')
  if(token) config.headers.Authorization = 'Bearer ' + token
  return config
})

export async function login(credentials){
  // expected {username, password} -> response { token, user }
  const res = await api.post('/auth/login', credentials)
  return res.data
}

export async function fetchUsers(){ return (await api.get('/users')).data }
export async function fetchPlans(){ return (await api.get('/building-plans')).data }
export async function fetchPlansByApplicant(id){ return (await api.get(`/building-plans/applicant/${id}`)).data }
export async function updatePlanStatus(id, status){ return (await api.patch(`/building-plans/${id}/status`, null, { params: { status } })).data }
export async function createReview(planId, reviewerId, review){ return (await api.post(`/reviews/plan/${planId}/reviewer/${reviewerId}`, review)).data }
export async function fetchReviewsByPlan(planId){ return (await api.get(`/reviews/plan/${planId}`)).data }

// file upload for building plan (multipart/form-data)
export async function uploadBuildingPlan(formData){ 
  return (await api.post('/building-plans', formData, { headers: {'Content-Type':'multipart/form-data'} })).data
}

export default { api, login, fetchUsers, fetchPlans, fetchPlansByApplicant, updatePlanStatus, createReview, fetchReviewsByPlan, uploadBuildingPlan }
