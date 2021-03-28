import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

const Toast = Swal.mixin({
  toast: true,
  position: 'top-end',
  showConfirmButton: false,
  showCloseButton: true,
  timer: 8000,
  timerProgressBar: true,
  didOpen: (toast) => {
    toast.addEventListener('mouseenter', Swal.stopTimer);
    toast.addEventListener('mouseleave', Swal.resumeTimer);
    toast.addEventListener('click', () => Swal.close());
  }
});

@Injectable({
  providedIn: 'root'
})
export class AlertModalService {

  constructor() {
  }

  toastError(message: string): void {
    Toast.fire({
      icon: 'error',
      title: message
    });
  }

  toastSuccess(message: string): void {
    Toast.fire({
      icon: 'success',
      title: message
    });
  }
}
