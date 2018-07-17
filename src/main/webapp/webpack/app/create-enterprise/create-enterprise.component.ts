import { Component, OnInit } from '@angular/core';
import { Enterprise } from '../enterprise';
import { EnterpriseService } from '../enterprise.service';

@Component({
  selector: 'app-create-enterprise',
  templateUrl: './create-enterprise.component.html',
  styleUrls: ['./create-enterprise.component.css']
})
export class CreateEnterpriseComponent implements OnInit {

  enterprise: Enterprise = new Enterprise();
  submitted = false;

  constructor(private enterpriseService: EnterpriseService) { }

  ngOnInit() {
  }

  newEnterprise(): void {
    this.submitted = false;
    this.enterprise = new Enterprise();
  }

  save() {
    this.enterpriseService.createEnterprise(this.enterprise)
      .subscribe(data => console.log(data), error => console.log(error));
    this.enterprise = new Enterprise();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
}

