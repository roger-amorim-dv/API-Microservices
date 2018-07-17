import { Component, OnInit } from '@angular/core';
import { Enterprise } from '../enterprise';
import { EnterpriseService } from '../enterprise.service';

@Component({
  selector: 'app-search-enterprise',
  templateUrl: './search-enterprise.component.html',
  styleUrls: ['./search-enterprise.component.css']
})
export class SearchEnterpriseComponent implements OnInit {

  enterprise: string;
  enterprises: Enterprise[];
 
  constructor(private dataService: EnterpriseService) { }
 
  ngOnInit() {
    this.enterprise = "";
  }
 
  private searchEnterprise() {
    this.dataService.getEnterprisesList(this.enterprise)
      .subscribe((res) => this.enterprise = res);
  }
 
  onSubmit() {
    this.searchEnterprise();
  }
}