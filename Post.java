
/* this is a container class containing information on each post */
public class Post
{
    /* The message contains the actual post message,
        the likes LL contains the names of people who have liked this post
        and the likeCount contains the total amount of likes */
    private String message;
    private DSALinkedList likes;
    private int likeCount;

    public Post(String message)
    {
        this.message = message;
        likes = new DSALinkedList();
        likeCount = 0;
    }

    public void addLike(String likedBy)
    {
        likes.insertLast(likedBy);
        likeCount++;
    }

    public void removeLike(String likedBy)
    {
        
    }

    public String getMessage()
    {
        return message;
    }

    public int getLikeCount()
    {
        return likeCount;
    }


}