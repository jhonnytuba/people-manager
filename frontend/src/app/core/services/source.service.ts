import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SourceService {

  constructor(private http: HttpClient) {
  }

  getSource(): Observable<any> {
    return this.http.get<any>(`${environment.API_URL}/source/`);
  }
}
