import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EnterpriseDetailsComponent } from './enterprise-details.component';

describe('EnterpriseDetailsComponent', () => {
  let component: EnterpriseDetailsComponent;
  let fixture: ComponentFixture<EnterpriseDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EnterpriseDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EnterpriseDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
