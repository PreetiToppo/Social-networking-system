import java.util.*;

/**
 * Represents a User in the social network.
 */
class User {
    private String name;
    private int age;
    private List<User> friends;
    private List<Post> posts;

    /**
     * Constructs a User with the given name and age.
     * 
     * @param name The name of the user.
     * @param age  The age of the user.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    /**
     * Get the name of the user.
     * 
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the age of the user.
     * 
     * @return The age of the user.
     */
    public int getAge() {
        return age;
    }

    /**
     * Get the list of friends of the user.
     * 
     * @return The list of friends of the user.
     */
    public List<User> getFriends() {
        return friends;
    }

    /**
     * Get the list of posts created by the user.
     * 
     * @return The list of posts created by the user.
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Add a friend to the user's friend list.
     * 
     * @param friend The user to be added as a friend.
     */
    public void addFriend(User friend) {
        friends.add(friend);
    }

    /**
     * Add a post to the user's post list.
     * 
     * @param post The post to be added.
     */
    public void addPost(Post post) {
        posts.add(post);
    }

    /**
     * Like a post by incrementing the like count.
     * 
     * @param post The post to be liked.
     */
    public void likePost(Post post) {
        post.addLike();
    }

    /**
     * Add a comment to a post.
     * 
     * @param post    The post to add a comment to.
     * @param comment The comment to be added.
     */
    public void commentOnPost(Post post, String comment) {
        post.addComment(comment);
    }
}

/**
 * Represents a post in the social network.
 */
class Post {
    private String content;
    private int likes;
    private List<String> comments;

    /**
     * Constructs a post with the given content.
     * 
     * @param content The content of the post.
     */
    public Post(String content) {
        this.content = content;
        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    /**
     * Get the content of the post.
     * 
     * @return The content of the post.
     */
    public String getContent() {
        return content;
    }

    /**
     * Get the number of likes on the post.
     * 
     * @return The number of likes on the post.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Get the list of comments on the post.
     * 
     * @return The list of comments on the post.
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Increment the like count of the post.
     */
    public void addLike() {
        likes++;
    }

    /**
     * Add a comment to the post.
     * 
     * @param comment The comment to be added.
     */
    public void addComment(String comment) {
        comments.add(comment);
    }
}

/**
 * Represents a social network.
 */
class SocialNetwork {
    private Map<String, User> users;
    private Map<User, List<Post>> newsFeed;

    /**
     * Constructs a social network with empty user and news feed maps.
     */
    public SocialNetwork() {
        this.users = new HashMap<>();
        this.newsFeed = new HashMap<>();
    }

    /**
     * Add a user to the social network.
     * 
     * @param user The user to be added.
     */
    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    /**
     * Get a user by their name.
     * 
     * @param name The name of the user.
     * @return The User object corresponding to the name.
     */
    public User getUser(String name) {
        return users.get(name);
    }

    /**
     * Update the news feed of a user by merging their friends' posts.
     * 
     * @param user The user whose news feed should be updated.
     */
    public void updateNewsFeed(User user) {
        List<Post> feed = new ArrayList<>();
        for (User friend : user.getFriends()) {
            feed.addAll(friend.getPosts());
        }
        newsFeed.put(user, feed);
    }

    /**
     * Get the news feed of a user.
     * 
     * @param user The user whose news feed is requested.
     * @return The list of posts in the user's news feed.
     */
    public List<Post> getNewsFeed(User user) {
        if (!newsFeed.containsKey(user)) {
            updateNewsFeed(user);
        }
        return newsFeed.get(user);
    }
}

public class Networking {
    public static void main(String[] args) {
        // Create social network
        SocialNetwork socialNetwork = new SocialNetwork();

        // Create users
        User user1 = new User("Alice", 25);
        User user2 = new User("Bob", 30);

        // Add users to the social network
        socialNetwork.addUser(user1);
        socialNetwork.addUser(user2);

        // Add friends
        user1.addFriend(user2);
        user2.addFriend(user1);

        // Create posts
        Post post1 = new Post("Hello, world!");
        Post post2 = new Post("I love coding!");

        // Add posts to users
        user1.addPost(post1);
        user2.addPost(post2);

        // Get news feed for user1
        List<Post> newsFeedUser1 = socialNetwork.getNewsFeed(user1);

        // Print the news feed for user1
        System.out.println("News feed for user1:");
        for (Post post : newsFeedUser1) {
            System.out.println(post.getContent());
        }

        // Get news feed for user2
        List<Post> newsFeedUser2 = socialNetwork.getNewsFeed(user2);

        // Print the news feed for user2
        System.out.println("News feed for user2:");
        for (Post post : newsFeedUser2) {
            System.out.println(post.getContent());
        }
    }
}
