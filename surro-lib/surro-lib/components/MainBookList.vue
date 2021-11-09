<template>   
        <div id="MainBookList">
            <div id="MainBookListTitle">{{bookTitle}}</div>
            <div>see all <bold>></bold> </div>
            <ul v-for="BookList in BookLists" :key="BookList.no"> 
                <NuxtLink><li>{{BookList.picture}}</li></NuxtLink>
                <li>{{BookList.category}}</li>
                <li>{{BookList.title}}</li>
                <li>{{BookList.writer}}</li>
            </ul>
        </div> 
</template>

<script>
export default {

};
</script>