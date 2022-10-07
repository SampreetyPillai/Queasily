const mongoose = require("mongoose");
const Schema  = mongoose.Schema;
 const blogSchema = new Schema({
    fname: {
        type: String,
        // required: True

    },
    lname:{
        type: String,
    },
    passw:{
        type: String,
    }
}, {timestamps :true}

 )

 const Blog = mongoose.model("Blog", blogSchema);
 module.exports = Blog;