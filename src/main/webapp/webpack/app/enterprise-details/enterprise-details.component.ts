import { Component, OnInit, Input } from '@angular/core';
import { Enterprise } from '../enterprise';
import { EnterpriseService } from '../enterprise.service';
import { EnterpriseListComponent } from '../enterprise-list/enterprise-list.component';

@Component({
  selector: 'app-enterprise-details',
  templateUrl: './enterprise-details.component.html',
  styleUrls: ['./enterprise-details.component.css']
})
export class EnterpriseDetailsComponent implements OnInit {

  @Input() enterprise: Enterprise;

  constructor(private enterpriseService: EnterpriseService, private listComponent: EnterpriseListComponent) { }

  ngOnInit() { }

  deleteEnterprise() {
    this.enterpriseService.deleteEnterprise(this.enterprise.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }
}
