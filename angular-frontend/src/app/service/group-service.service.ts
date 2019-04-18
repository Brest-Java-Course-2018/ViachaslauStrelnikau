/**
 * GroupService perform http operations with groups
 */
import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GroupDto} from "../model/group-dto";
import {Group} from "../model/group";
import {GroupDtoLite} from "../model/group-dto-lite";
const BASE_URL='http://127.0.0.1:8088/groups/';
const BASE_URL_DTO='http://127.0.0.1:8088/groupsdto/';
@Injectable({
  providedIn: 'root'
})
export class GroupServiceService {

  constructor(private httpClient:HttpClient) { }

  /**
   * gets group record by its ID
   * @param id
   */
  getGroup(id:string): Observable<Group>
  {
    let result=this.httpClient.get(BASE_URL+id);
    return result as Observable<Group>
  }

  /**
   * gets groups list
   */
  getGroups(): Observable<GroupDto[]>
  {
    let result=this.httpClient.get(BASE_URL);
    return result as Observable<GroupDto[]>
  }

  /**
   * removes groups record from DB
   * @param id group ID
   */
  removeGroup(id:string)
  {
    return this.httpClient.delete(BASE_URL+id);
  }

  /**
   * adds group record to DB
   * @param group Group object
   */
  addGroup(group:Group)
  {
    return this.httpClient.post(BASE_URL,group);
  }

  /**
   * updates group record in DB
   * @param group Group object
   */
  updateGroup(group:Group)
  {
    return this.httpClient.post(BASE_URL+group.groupId,group);
  }

  /**
   * gets brief groups list
   */
  getGroupsList():Observable<GroupDtoLite[]>
  {
    let result=this.httpClient.get(BASE_URL_DTO);
    return result as Observable<GroupDtoLite[]>
  }

}
