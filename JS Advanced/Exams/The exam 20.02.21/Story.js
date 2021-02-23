class Story {
    #comments;
    #likes;
    constructor(title, creator){
        this.title = title;
        this.creator = creator;
        this.#comments = [];
        this.#likes = [];
    }

    get likes(){
        if(this.#likes.length === 0){
            return `${this.title} has 0 likes`;
        }
        if(this.#likes.length === 1){
            return `${this.#likes[0]} likes this story!`;
        }
        if(this.#likes.length > 1){
            return `${this.#likes[0]} and ${this.#likes.length - 1} others like this story!`;
        }

    }

    like(username){
        if(this.#likes.includes(username)){
            throw new Error("You can't like the same story twice!");
        }
        if(username===this.creator){
            throw new Error("You can't like your own story!");
        }
        this.#likes.push(username);
        return `${username} liked ${this.title}!`;
    }
    dislike(username){
        if(!this.#likes.includes(username)){
            throw new Error("You can't dislike this story!");
        }
        let found = this.#likes.find(x => x === username);
        let index = this.#likes.indexOf(found);
        this.#likes.splice(index,1);
        return `${username} disliked ${this.title}`;
    }
    comment(username, content, id){
        let found = this.#comments.find(x=> x.id === id);
        let indexOfComment = this.#comments.length+1;
        if(id === undefined || found === undefined){
            //add new comment
            this.#comments.push({id:indexOfComment,username,content,replies : []});
           
            return `${username} commented on ${this.title}`;
        } else {
            let reply = {id: found.id + (found.replies.length===0 ? 0.1 :(found.replies.length/10)+0.1),username,content};
            
            //add new reply to existing comment
            found.replies.push(reply);
            return "You replied successfully";
        }
    }
    toString(sortingType){
        let out = '';
        out += `Title: ${this.title}\n`;
        out += `Creator: ${this.creator}\n`;
        out += `Likes: ${this.#likes.length}\n`
        out += `Comments:\n`
        if(sortingType === 'asc'){
            // sorting is not needed
            this.#comments.map((x)=>{
                out += `-- ${x.id}. ${x.username}: ${x.content}\n`
                if(x.replies.length !== 0){
                    x.replies.map((y)=>{
                        out+= `--- ${y.id}. ${y.username}: ${y.content}\n`;
                    })
                }
            })
            
        }
        if(sortingType === 'desc'){
            let desc = this.#comments.sort((a,b)=>b.id - a.id);
            desc.map((x)=>{
                out += `-- ${x.id}. ${x.username}: ${x.content}\n`;
                if(x.replies.length !== 0){
                    let descReplies = x.replies.sort((a,b)=> b.id - a.id);
                    descReplies.map((y)=>{
                        out+= `--- ${y.id}. ${y.username}: ${y.content}\n`;
                    })
                }
            })
        }
        if(sortingType === 'username'){
            let nameSort = this.#comments.sort((a, b)=>a.username.localeCompare(b.username));
            nameSort.map((x)=>{
                out += `-- ${x.id}. ${x.username}: ${x.content}\n`;
                if(x.replies.length !== 0){
                    let nameReplies = x.replies.sort((a,b)=> a.username.localeCompare(b.username));
                    nameReplies.map((y)=>{
                        out+= `--- ${y.id}. ${y.username}: ${y.content}\n`;
                    })
                }
            })
        }
        return out.trim();
    }
}