import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Person } from '../models/person';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  constructor(private http: HttpClient) {
  }

  private static getApiUrl(): string {
    return `${environment.API_URL}/v2/people/`;
  }

  findAll(): Observable<Person[]> {
    return this.http.get<Person[]>(PeopleService.getApiUrl());
  }

  findById(id: number): Observable<Person> {
    return this.http.get<Person>(PeopleService.getApiUrl() + id);
  }

  persist(person: Person): Observable<Person> {
    return this.http.post<Person>(PeopleService.getApiUrl(), person);
  }

  update(person: Person, id: number): Observable<Person> {
    return this.http.put<Person>(PeopleService.getApiUrl() + id, person);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(PeopleService.getApiUrl() + id);
  }
}
