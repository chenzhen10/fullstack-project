import { baseUrl } from './../../environments/environment'
import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor (private http:HttpClient) { }

  login (data):Observable<any> {
    return this.http.post(`${baseUrl}/authentication/sign-in`, data)
  }
}
