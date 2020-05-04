import { Unit } from './Unit'
import { SensorType } from './SensorType'
import { Sensor } from './sensor'
import { RouterService } from './../router/router.service'
import { baseUrl } from './../../environments/environment'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { HttpClient, HttpHeaders } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class SensorService {
 token = localStorage.getItem('token')
 headers = new HttpHeaders({ Authorization: 'Bearer ' + this.token })
 constructor (private http:HttpClient, private router: RouterService) { }

 getAll (pageNumber: number) {
   return this.http.get<Sensor[]>(`${baseUrl}/sensors?page=${pageNumber}&size=4`, { headers: this.headers })
 }

 search (param:string):Observable<any> {
   return this.http.get(`${baseUrl}/sensors/search?searchCriteria=${param}&size=4`, { headers: this.headers })
 }

 deleteById (id: number):Observable<any> {
   return this.http.delete(`${baseUrl}/sensors/${id}`, { headers: this.headers })
 }

 getById (id: number):Observable<Sensor> {
   return this.http.get<Sensor>(`${baseUrl}/sensors/${id}`, { headers: this.headers })
 }

 create (sensor: Sensor):Observable<any> {
   return this.http.post(`${baseUrl}/sensors`, sensor, { headers: this.headers })
 }

 update (sensor: Sensor, id:number):Observable<any> {
   return this.http.put(`${baseUrl}/sensors/${id}`, sensor, { headers: this.headers })
 }

 logout () {
   localStorage.clear()
   this.router.toLoginScreen()
 }

 typeConverter (type) {
   return type.charAt(0) + type.slice(1).toLowerCase()
 }

 unitConverter (unit) {
   let result
   if (unit === 'BAR') {
     result = unit.toLowerCase()
   } else if (unit === 'VOLTAGE') {
     result = unit.toLowerCase()
   } else if (unit === 'CELSIUS') {
     result = '°C'
   } else {
     result = '%'
   }
   return result
 }

 getTypes () {
   const result: SensorType[] = []
   this.http.get<string[]>(`${baseUrl}/sensors/types`, { headers: this.headers }).subscribe(res => res.map(type => result.push({ uiType: this.typeConverter(type), type })))
   return result
 }

 getUnits () {
   const result: Unit[] = []
   this.http.get<string[]>(`${baseUrl}/sensors/units`, { headers: this.headers }).subscribe(res => res.map(unit => result.push({ uiUnit: this.unitConverter(unit), unit })))
   return result
 }
}
