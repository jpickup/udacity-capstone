<template>
        <div class="spinner">
            <img src="green-spinner.gif" height="75" width="75"/>
            <slot/>
        </div>
</template>

<script>
export default {
    name: 'LoadingSpinner',
}
</script>

<style scoped>
.spinner {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-evenly;
}

</style>