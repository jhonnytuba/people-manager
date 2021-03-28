import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({name: 'personGender'})
export class PersonGenderPipe implements PipeTransform {

    constructor(private sanitizer: DomSanitizer) {
    }

    transform(value: string): string {
      if (!value) {
        return value;
      }
      switch (value) {
        case 'MALE': return 'Masculino';
        case 'FEMALE': return 'Feminino';
      }
      return 'Neutro';
    }
}
