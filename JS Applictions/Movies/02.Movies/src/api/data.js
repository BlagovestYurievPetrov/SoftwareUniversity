import * as api from './api.js';

const host = 'http://localhost:3030';
api.settings.host = host;

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

export async function getMovies() {
    return await api.get(host + '/data/movies ');
}

export async function addMovie(data) {
    return await api.post(host + '/data/movies', data);
}


export async function getItemById(id) {
    return await api.get(host + '/data/movies/' + id);
}

export async function deleteRecord(id){
    return await api.del(host + '/data/movies/' + id);
}

export async function editRecords(id, data){
    return await api.put(host + '/data/movies/' + id, data);
}