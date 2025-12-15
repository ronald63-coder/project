import jwtDecode from 'jwt-decode'

export function saveAuth(token){ localStorage.setItem('token', token); try{ const u = jwtDecode(token); localStorage.setItem('currentUser', JSON.stringify(u)); }catch(e){} }
export function clearAuth(){ localStorage.removeItem('token'); localStorage.removeItem('currentUser') }
export function getCurrentUser(){ try{ return JSON.parse(localStorage.getItem('currentUser')) }catch(e){ return null } }
