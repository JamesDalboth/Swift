#version 330 core

in DATA
{
	vec2 tc;
	vec3 position;
} fs_in;

layout (location = 0) out vec4 color;
const vec2 resolution = vec2(1920,1080);
const vec2 sun = vec2(800,800);

void main()
{
    vec2 uv = fs_in.position.xy * resolution;
    vec2 p = ( 2.0 * fs_in.position.xy - vec2(1920.0f,1080.0f)) * vec2(1920.0f,1080.0f) / 1000.0;

    // background
	vec3 fragColor = mix( vec3( 131/250.0f, 219/250.0f, 255/255.0f ), vec3( 255/250.0f, 150/250.0f, 0/255.0f ), dot( fs_in.position.xy, vec2( 0.0, -0.5) ) );

    color += vec4( fragColor, 1.0 );

    if (distance(uv,sun) < 100) {
        color = vec4( 255/250.0f, 252/250.0f, 160/255.0f ,1.0);
    }

}