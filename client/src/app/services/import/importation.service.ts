import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { env } from '../../../environment/env';

@Injectable({
  providedIn: 'root',
})
export class ImportationService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  private errorSubject = new BehaviorSubject<string | null>(null);
  public error$ = this.errorSubject.asObservable();
  private messageSubject = new BehaviorSubject<string | null>(null);
  public message$ = this.messageSubject.asObservable();

  constructor(public http: HttpClient) {}

  uploadStageAndResultFiles(
    stageFile: File,
    resultFile: File
  ): Observable<any> {
    let formData = new FormData();
    formData.append('files', stageFile);
    formData.append('files', resultFile);
    this.loadingSubject.next(true);
    return this.http
      .post(`${env.api}/admin/imports/stages-results`, formData)
      .pipe(
        tap((response: any) => {
          this.loadingSubject.next(false);
          this.messageSubject.next(response.message || null);
          setTimeout(() => {
            this.messageSubject.next(null);
          }, 5000);
        }),
        catchError((error) => {
          this.loadingSubject.next(false);
          this.errorSubject.next(error.message);
          setTimeout(() => {
            this.errorSubject.next(null);
          }, 5000);
          return throwError(() => error);
        })
      );
  }

  uploadPointFile(pointFile: File): Observable<any> {
    let formData = new FormData();
    formData.append('file', pointFile);
    this.loadingSubject.next(true);
    return this.http.post(`${env.api}/admin/imports/points`, formData).pipe(
      tap((response: any) => {
        this.loadingSubject.next(false);
        this.messageSubject.next(response.message || null);
        setTimeout(() => {
          this.messageSubject.next(null);
        }, 5000);
      }),
      catchError((error) => {
        this.loadingSubject.next(false);
        this.errorSubject.next(error.message);
        setTimeout(() => {
          this.errorSubject.next(null);
        }, 5000);
        return throwError(() => error);
      })
    );
  }
}
