import { UserLogged } from "./user-logged";

export interface AuthResponse {
    token: string;
    user: UserLogged;
  }