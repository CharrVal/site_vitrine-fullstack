import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
  const idToken = localStorage.getItem("Jwt");

  if (idToken) {
    const cloned = req.clone({
        headers: req.headers.set("Authorization",
            "Bearer " + idToken)
    });
    return next.call("handle", cloned);
    } else {
    return next.call("handle", req);
    }
};