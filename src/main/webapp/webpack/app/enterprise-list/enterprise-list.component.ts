import { Component, OnInit } from '@angular/core';
import { Enterprise } from '../enterprise';
import { Observable } from 'rxjs/Observable';
import { EnterpriseService } from '../enterprise.service';

@Component({
  selector: 'app-enterprise-list',
  templateUrl: './enterprise-list.component.html',
  styleUrls: ['./enterprise-list.component.css']
})
export class EnterpriseListComponent implements OnInit {

  enterprise: Observable<Enterprise[]>;

  constructor(private enterpriseService: EnterpriseService) { }

  ngOnInit() {
    this.reloadData();
  }

  deleteCustomers() {
    this.enterpriseService.deleteAll()
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log('ERROR: ' + error));
  }

  reloadData() {
    this.enterprise = this.enterpriseService.getEnterprisesList();
  }
}
