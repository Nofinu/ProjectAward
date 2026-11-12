import type { PageServerLoad } from './$types';
import { redirect } from '@sveltejs/kit';

export const load: PageServerLoad = async ({ cookies }) => {
    cookies.delete('twitch_token', { path: '/' });
    cookies.delete('twitch_username', { path: '/' });
    redirect(303, '/'); 
};