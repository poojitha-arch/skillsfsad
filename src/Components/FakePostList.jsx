import React, { useState, useEffect } from "react";
import axios from "axios";

function FakePostList() {

  const [posts, setPosts] = useState([]);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [userId, setUserId] = useState("all");

  const fetchPosts = () => {
    axios.get("https://dummyjson.com/posts")
      .then((res) => {
        setPosts(res.data.posts);
        setFilteredPosts(res.data.posts);
      });
  };

  useEffect(() => {
    fetchPosts();
  }, []);

  const filterPosts = (id) => {

    setUserId(id);

    if (id === "all") {
      setFilteredPosts(posts);
    } else {
      const filtered = posts.filter(post => post.userId === parseInt(id));
      setFilteredPosts(filtered);
    }
  };

  return (
    <div>

      <h2>Fake API Posts</h2>

      <button onClick={fetchPosts}>Refresh</button>

      <br/><br/>

      <select value={userId} onChange={(e) => filterPosts(e.target.value)}>
        <option value="all">All Users</option>
        <option value="1">User 1</option>
        <option value="2">User 2</option>
        <option value="3">User 3</option>
      </select>

      {filteredPosts.map((post) => (
        <div key={post.id} className="card">
          <h4>{post.title}</h4>
          <p>{post.body}</p>
        </div>
      ))}

    </div>
  );
}

export default FakePostList;