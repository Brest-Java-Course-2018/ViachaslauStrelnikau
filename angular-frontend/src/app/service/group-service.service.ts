import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GroupDto} from "../model/group-dto";
import {Group} from "../model/group";
const BASE_URL='http://127.0.0.1:8088/groups/';
@Injectable({
  providedIn: 'root'
})
export class GroupServiceService {

  constructor(private httpClient:HttpClient) { }

  getGroup(num:string): Observable<Group>
  {
    let result=this.httpClient.get(BASE_URL+num);
    return result as Observable<Group>
  }
  getGroups(): Observable<GroupDto[]>
  {
    let result=this.httpClient.get(BASE_URL);
    return result as Observable<GroupDto[]>
  }
  removeGroup(num:string)
  {
    return this.httpClient.delete(BASE_URL+num);
  }

  addGroup(group:Group)
  {
    return this.httpClient.post(BASE_URL,group);
  }
  updateGroup(group:Group)
  {
    return this.httpClient.post(BASE_URL+group.groupId,group);
  }

}
