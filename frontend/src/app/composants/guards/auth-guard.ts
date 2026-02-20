import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  let router = inject(Router);

  if(sessionStorage.getItem("user") != null){
    return true;
  }

  router.navigate(['/connexion']);
  return false;
};
