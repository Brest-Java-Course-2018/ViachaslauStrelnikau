import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GroupDto} from "../model/group-dto";
import {map} from "rxjs/operators";
const BASE_URL='http://127.0.0.1:8088/groups';
@Injectable({
  providedIn: 'root'
})
export class GroupServiceService {

  constructor(private httpClient:HttpClient) { }
  getGroups(): Observable<GroupDto[]>
  {
    let result=this.httpClient.get(BASE_URL);
    return result as Observable<GroupDto[]>
  }

}
