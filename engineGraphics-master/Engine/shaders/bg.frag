#version 330 core

in DATA
{
	vec2 tc;
	vec3 position;
} fs_in;

layout (location = 0) out vec4 color;
const vec2 resolution = vec2(1920,1080);
const vec2 sun = vec2(80,80);


void main()
{
    vec2 uv = fs_in.position.xy * resolution;
    vec2 p = ( 2.0 * fs_in.position.xy - vec2(1920.0f,1080.0f)) * vec2(1920.0f,1080.0f) / 1000.0;

    // background
	vec3 fragColor = mix( vec3( 131/250.0f, 219/250.0f, 255/255.0f ), vec3( 255/250.0f, 150/250.0f, 0/255.0f ), dot( fs_in.position.xy, vec2( 0.0, -0.5) ) );

    color += vec4( fragColor, 1.0);



    vec3 bw = vec3((color.r + color.g + color.b) / 3.0f);

    vec3 delta = (color.xyz - bw) * (fs_in.tc.x) / 10000.0f;

    color = vec4(color.xyz - delta,1.0);
}