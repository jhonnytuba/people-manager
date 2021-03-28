import { Component, OnInit } from '@angular/core';
import { SourceService } from '../core/services/source.service';

@Component({
  selector: 'app-source',
  templateUrl: './source.component.html',
  styleUrls: ['./source.component.scss']
})
export class SourceComponent implements OnInit {

  urlGithub = '';

  constructor(private sourceService: SourceService) {
  }

  ngOnInit(): void {
    this.sourceService.getSource()
      .subscribe(value => this.urlGithub = value.url);
  }
}
