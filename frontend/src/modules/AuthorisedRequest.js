import axios from 'axios';
import { useUserDataStore } from '@/stores/UserData.js';

function get(url, ...args) {
    console.log(url);
    const userDataStore = useUserDataStore()
    const headers= {};
    if (userDataStore.isLoggedIn) {
        headers.Authorization = 'Bearer ' + userDataStore.credential;
      }
    for (var attrname in args) { headers[attrname] = args[attrname]; }
    return axios.get(url, {headers});
}

function post(url, data, ...args) {
  const userDataStore = useUserDataStore()
    const headers= {};
    if (userDataStore.isLoggedIn) {
        headers.Authorization = 'Bearer ' + userDataStore.credential;
      }
    for (var attrname in args) { headers[attrname] = args[attrname]; }
    return axios.post(url, data, {headers});
}

function put(url, data, ...args) {
  const userDataStore = useUserDataStore()
    const headers= {};
    if (userDataStore.isLoggedIn) {
        headers.Authorization = 'Bearer ' + userDataStore.credential;
      }
    for (var attrname in args) { headers[attrname] = args[attrname]; }
    return axios.put(url, data, {headers});
}
function sendDelete(url, ...args) {
  const userDataStore = useUserDataStore()
    const headers= {};
    if (userDataStore.isLoggedIn) {
        headers.Authorization = 'Bearer ' + userDataStore.credential;
        console.log(headers.Authorization);
      }
    for (var attrname in args) { headers[attrname] = args[attrname]; }
    return axios.delete(url, {headers});
}

export {get, post, put, sendDelete}