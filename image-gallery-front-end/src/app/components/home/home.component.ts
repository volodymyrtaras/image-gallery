import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/rest/user/user.service';
import {Router} from '@angular/router';
import {User} from '../../model/user/user';
import {ImageService} from '../../services/rest/image/image.service';
import {paths} from '../../resources/resources';
import {HttpClient, HttpEventType} from '@angular/common/http';
import {Image} from '../../model/image/image';
import {newArray} from '@angular/compiler/src/util';
import {Tag} from '../../model/tag/tag';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: User;
  images: Image[];
  tags: Tag[];
  defaultTagValue = '';
  fileData: File = null;
  previewUrl: any = null;
  fileUploadProgress: string = null;
  uploadedFilePath: string = null;
  searchValue = '';

  constructor(private userService: UserService, private router: Router, private imageService: ImageService, private http: HttpClient) {
    setTimeout(() => this.getUser(), 500);

    this.tags = newArray(0);

    this.imageService.getAllImages().subscribe(
      data => this.images = data
    );
  }

  ngOnInit(): void {

  }

  logout(): void {
    window.sessionStorage.setItem('loggedIn', 'false');
    this.userService.logout();
    this.router.navigate(['']);
  }

  getUser(): User {
    this.userService.getCurrentUser().subscribe(
      data => this.user = data);
    return this.user;
  }

  fileProgress(fileInput: any): void {
    this.tags = [];
    this.fileData = (fileInput.target.files[0] as File);
    this.preview();
  }

  preview(): void {
    const mimeType = this.fileData.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(this.fileData);
    reader.onload = () => {
      this.previewUrl = reader.result;
    };
  }

  onSubmit(): void {
    const formData = new FormData();
    formData.append('file', this.fileData);
    this.fileUploadProgress = '0%';

    this.http.post<any>(paths.api + '/' + paths.image, formData, {
      reportProgress: true,
      observe: 'events'
    })
      .subscribe(events => {
        if (events.type === HttpEventType.UploadProgress) {
          this.fileUploadProgress = Math.round(events.loaded / events.total * 100) + '%';
        } else if (events.type === HttpEventType.Response) {
          const image: Image = events.body;
          this.fileUploadProgress = '';
          this.previewUrl = null;
          this.fileUploadProgress = null;
          this.uploadedFilePath = null;
          this.fileData = null;

          let currentImageId = null;
          this.http.get(paths.api + '/' + paths.currentImage).subscribe(data => {
            currentImageId = data;
            image.id = currentImageId;
          });
          image.tags = this.tags;

          this.http.post<any>(paths.api + '/' + paths.currentImage + '/' + image.id, image).subscribe(
            data => {
              console.log('Success');
              this.tags = [];
              this.reload();

            },
            error => console.log(error)
          );
        }
      });
  }

  goToImageUrl(url: string): void {
    window.open(url);
  }

  addTag(event: any): void {
    const tag: Tag = new Tag();
    this.defaultTagValue = event.target.value;
    tag.name = this.defaultTagValue;
    this.tags.push(tag);
    this.defaultTagValue = '';
  }

  addSearchTag(event: any): void {
    this.searchValue = event.target.value;
  }

  onSearch(): void {
    this.images = newArray(0);
    for (const image of this.user.images) {
      for (const tag of image.tags) {
        if (tag.name === this.searchValue) {
          this.images.push(image);
        }
      }
    }
  }

  reload(): void {
    window.location.reload();
  }
}
