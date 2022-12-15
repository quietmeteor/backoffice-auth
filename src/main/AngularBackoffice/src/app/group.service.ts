import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {  Group } from './group';
@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private url = "http://localhost:8080/api/groups";
  constructor(private client: HttpClient) {
  }

  getAllGroups(): Observable<Group[]> {
    return this.client.get<Group[]>("http://localhost:8080/api/groups");
  }

/* Object return

  getAllGroups(): Observable<Object> {
    return this.client.get("http://localhost:8080/api/groups");
  }

  getGroupById(id: number): Observable<Object> {
    return this.client.get("http://localhost:8080/api/groups/" + id);
  }
*/

  getGroupById(id: number): Observable<Group> {
    return this.client.get<Group>("http://localhost:8080/api/groups/" + id);
  }

  createGroup(group: Group): Observable<Object> {
    return this.client.post("http://localhost:8080/api/groups", group);
  }


  deleteGroupById(id: number): Observable<Object> {
    return this.client.delete("http://localhost:8080/api/groups/" + id);
  }

  updateGroup(id: number, group: Group): Observable<Object> {

    return this.client.put("http://localhost:8080/api/groups/" + id, group);
  }


}
