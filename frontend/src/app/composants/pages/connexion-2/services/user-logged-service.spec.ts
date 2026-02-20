import { TestBed } from '@angular/core/testing';

import { UserLoggedService } from '../../connexion-2/services/user-logged-service';

describe('UserLoggedService', () => {
  let service: UserLoggedService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserLoggedService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
