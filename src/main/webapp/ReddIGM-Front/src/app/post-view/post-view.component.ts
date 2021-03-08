import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { faPlusCircle, faMinusCircle, faTimesCircle } from '@fortawesome/free-solid-svg-icons';
import { AuthenticationService } from '../services/authentication.service';
import { VoteType } from '../models/vote-type.enum';
import { PostService } from "../services/post.service"

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {
  faPlusCircle = faPlusCircle;
  faMinusCircle = faMinusCircle;
  faTimesCircle = faTimesCircle;

  upvote: VoteType = VoteType.UPVOTE;
  downvote: VoteType = VoteType.DOWNVOTE;
  novote: VoteType = VoteType.NOVOTE;
  isAuthentified: boolean = false;
  isAdmin: boolean | undefined = false;

  @Input() id: number = 8;
  @Input() author: string = "Michel"; //
  @Input() title: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() content: string = "BDK ou BDE quelle association est la plus éclatée ?"; //
  @Input() date: string = "5 days ago"; //
  @Input() subName: string = "r/truc"; //
  @Input() rate: number = 14; //utiliser number
  @Input() image: string = "https://ih1.redbubble.net/image.698410235.0273/flat,128x128,075,t.u2.jpg"; //utiliser number
  @Input() vote: VoteType = VoteType.NOVOTE;
  @Input() subId: number | undefined = 0;
  @Input() authorId: number = 0;
  @Input() parentRoute: string = ""


  constructor(private router: Router, private authenticationService: AuthenticationService, private postService: PostService) {
    this.isAuthentified = this.authenticationService.isLoggedIn();
    this.isAdmin = this.authenticationService.getCurrentUser()?.isAdmin()
  }

  ngOnInit(): void {
  }

  displayPost(){
    this.router.navigate(['display-post', { id: this.id, subName: this.subName, subId: this.subId }]);
  }

  clickVote(userVote: VoteType) {
    if(!this.isAuthentified) {
      this.router.navigateByUrl('/login');
    }
    this.postService.vote(this.id, userVote).subscribe(data => {this.vote = data.myVote; this.rate = data.voteCount;});
  }

  clickSub() {
    this.router.navigateByUrl('posts-from-sub/'+this.subId);
  }

  clickProfile() {
    this.router.navigateByUrl('users/'+this.authorId);
  }

  deleteId(id: number) {
    this.postService.deletePost(id).subscribe();
    this.router.navigateByUrl('/RefreshComponent', { skipLocationChange: true }).then(() => {
      this.router.navigate([this.parentRoute]);
  });
  }
}
