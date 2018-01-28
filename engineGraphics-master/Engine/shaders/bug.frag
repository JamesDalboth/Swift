#version 330 core

layout (location = 0) out vec4 color;

in DATA
{
	vec2 tc;
	vec2 time;
} fs_in;

const vec2 center = vec2(0.5,0.5);
void main()
{
	float dist = abs(distance(center,fs_in.tc));

    if (dist < 0.4) {
        color = vec4(255.0f/255.0f,255.0f/255.0f,200.f/255.0f,0.125f/dist);
    } else {
        color = vec4(0.0f);
    }

    if (dist < 0.5) {
        color += vec4(255.0f/255.0f,255.0f/255.0f,200.f/255.0f,0.125f/dist);
    } else {
        color += vec4(0.0f);
    }

    color -= vec4(1.0 * fs_in.time.x, 0.0 * fs_in.time.x,1.0 * fs_in.time.x,0.0f);
}