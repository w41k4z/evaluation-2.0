import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, tap, throwError } from 'rxjs';
import { env } from '../../../environment/env';

@Injectable({
  providedIn: 'root',
})
export class AssignRunnerTimeService {
  private loadingSubject = new BehaviorSubject<boolean>(false);
  public loading$ = this.loadingSubject.asObservable();
  private errorSubject = new BehaviorSubject<string | null>(null);
  public error$ = this.errorSubject.asObservable();
  private messageSubject = new BehaviorSubject<string | null>(null);
  public message$ = this.messageSubject.asObservable();

  constructor(public http: HttpClient) {}

  list(stageId: string) {
    this.loadingSubject.next(true);
    return this.http.get(`${env.api}/api/v1/stages/${stageId}/runners`).pipe(
      tap((response: any) => {
        this.loadingSubject.next(false);
        this.messageSubject.next(response.message || null);
        setTimeout(() => {
          this.messageSubject.next(null);
        }, 5000);
      }),
      catchError((error) => {
        this.loadingSubject.next(false);
        this.errorSubject.next(error.error.errors[0]);
        setTimeout(() => {
          this.errorSubject.next(null);
        }, 5000);
        return throwError(() => error);
      })
    );
  }

  assign(stageRunnersId: string, startTime: string, endTime: string) {
    this.loadingSubject.next(true);
    return this.http
      .post(`${env.api}/api/v1/stages/assign-time`, {
        stageRunnersId,
        startTime,
        endTime,
      })
      .pipe(
        tap((response: any) => {
          this.loadingSubject.next(false);
          this.messageSubject.next(response.message);
          setTimeout(() => {
            this.messageSubject.next(null);
          }, 5000);
        }),
        catchError((error) => {
          this.loadingSubject.next(false);
          this.errorSubject.next(error.error.errors[0]);
          setTimeout(() => {
            this.errorSubject.next(null);
          }, 5000);
          return throwError(() => error);
        })
      );
  }
}
