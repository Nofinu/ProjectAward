import type { PageServerLoad } from './$types';

export const load: PageServerLoad = async ({ cookies, fetch }) => {
  const token = cookies.get('twitch_token');

  if (!token) {
    return { validation: null };
  }

  const res = await fetch('https://id.twitch.tv/oauth2/validate', {
    headers: { Authorization: `OAuth ${token}` }
  });

  if (!res.ok) {
    // token expiré ou révoqué : on nettoie les cookies
    cookies.delete('twitch_token', { path: '/' });
    cookies.delete('twitch_username', { path: '/' });
    return { validation: null };
  }

  return { validation: await res.json() };
};