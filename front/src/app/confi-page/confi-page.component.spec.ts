import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfiPageComponent } from './confi-page.component';

describe('ConfiPageComponent', () => {
  let component: ConfiPageComponent;
  let fixture: ComponentFixture<ConfiPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfiPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfiPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
