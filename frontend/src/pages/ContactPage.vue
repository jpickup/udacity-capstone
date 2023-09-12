<template>
    <full-page title="Contact">
        <p>Email us at <a href="mailto:contact@shrc.uk">contact@udacity-routes.com</a>
        </p>
    </full-page>
</template>

<script>

import FullPage from '@/components/page/FullPage';

export default {
    name: 'ContactPage',
    components: {
        FullPage
    },
};
</script>

<style scoped>
p {
    text-align: left;
    margin: 10px;
    font-size: 1.5rem;
}

h2 {
    margin: 10px;
    text-align: left;
    font-size: 2.5rem;
}
</style>