import { Component, OnInit } from '@angular/core';
import {PostService} from "../services/post.service"
import {PostModel} from "../models/post.model"
import { ActivatedRoute } from '@angular/router';
import {SubjectService} from "../services/subject.service"
import {SubjectModel} from "../models/subject-response"
import {UserService} from "../services/user.service"
import {User} from "../models/user.model"

@Component({
  selector: 'app-posts-from-sub',
  templateUrl: './posts-from-sub.component.html',
  styleUrls: ['./posts-from-sub.component.scss']
})
export class PostsFromSubComponent implements OnInit {
  posts: Array<PostModel> = [];
  subId: number;
  subName: string = "";
  subDescription: string = "";

  constructor(private postService: PostService, private subjectService: SubjectService, private userService: UserService, private route: ActivatedRoute) {
    this.subId = this.route.snapshot.params['id'];
    this.postService.getPostsBySubject(this.subId).subscribe(data => {this.posts = data;});
    this.subjectService.getSubject(this.subId).subscribe(data => {this.subName = data.name; this.subDescription = data.description;});
  }

  ngOnInit(): void {
  }

  getUser(userId: number): User {
    let user: User = new User();
    this.userService.getUserById(userId).subscribe(data => user = data);
    return user;
  }
}
