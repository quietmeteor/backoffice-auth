import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private client: HttpClient) {

  }

  getAllUsers(): Observable<Object> {
    return this.client.get("http://localhost:8080/api/users")

  }

  getUserById(id: number): Observable<Object> {
    return this.client.get("http://localhost:8080/api/users/" + id);
  }

  getUserByUsername(username: string): Observable<Object> {
    return this.client.get("http://localhost:8080/api/users/" + username);
  }

  createUser(user: User): Observable<Object> {
    return this.client.post("http://localhost:8080/api/users", user);
  }

  updateUser(id:number, user:User):Observable<Object>{
    return this.client.put("http://localhost:8080/api/users/"+id, user);

  }

  deleteUserById(id:number):Observable<Object>{
    return this.client.delete("http://localhost:8080/api/users/"+id)
  }

  //Not sure
  addUserToGroup(idGroup:number, idUser:number):Observable<Object>{
    return this.client.put("http://localhost:8080/api/users/"+idGroup+"/"+idUser, idGroup, idUser);
  }


}
