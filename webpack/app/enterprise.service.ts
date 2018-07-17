import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { HttpClient } from "@angular/common/http";


@Injectable()
export class EnterpriseService{

    private baseUrl = 'http://localhost:8080/api/enterprise';

    constructor(private http: HttpClient){}

      getEnterprise(id : string): Observable<Object>{
        return this.http.get(`${this.baseUrl}/${id}`);
      }

      createEnterprise(enterprise: Object): Observable<Object> {
        return this.http.post(`${this.baseUrl}` + `/create`, enterprise);
      }
     
      updateEnterprise(id: string, value: any): Observable<Object> {
        return this.http.put(`${this.baseUrl}/${id}`, value);
      }
     
      deleteEnterprise(id: string): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
      }
     
      getEnterprisesList(query = {}): Observable<any> {
        return this.http.get(`${this.baseUrl}`);
      }
     
      deleteAll(): Observable<any> {
        return this.http.delete(`${this.baseUrl}` + `/delete`, { responseType: 'text' });
      } 
}